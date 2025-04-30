/*
 *  Copyright (c) 2023-2024 Sunesis and/or its affiliates
 *  and other contributors as indicated by the @author tags and
 *  the contributor list.
 *
 *  Licensed under the MIT License (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *  https://opensource.org/licenses/MIT
 *
 *  The software is provided "AS IS", WITHOUT WARRANTY OF ANY KIND, express or
 *  implied, including but not limited to the warranties of merchantability,
 *  fitness for a particular purpose and noninfringement. in no event shall the
 *  authors or copyright holders be liable for any claim, damages or other
 *  liability, whether in an action of contract, tort or otherwise, arising from,
 *  out of or in connection with the software or the use or other dealings in the
 *  software. See the License for the specific language governing permissions and
 *  limitations under the License.
 */
package si.sunesis.interoperability.modbus;

import com.intelligt.modbus.jlibmodbus.Modbus;
import com.intelligt.modbus.jlibmodbus.exception.ModbusIOException;
import com.intelligt.modbus.jlibmodbus.master.ModbusMaster;
import com.intelligt.modbus.jlibmodbus.msg.base.ModbusRequest;
import com.intelligt.modbus.jlibmodbus.msg.base.ModbusResponse;
import com.intelligt.modbus.jlibmodbus.utils.FrameEvent;
import com.intelligt.modbus.jlibmodbus.utils.FrameEventListener;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import si.sunesis.interoperability.common.AbstractRequestHandler;
import si.sunesis.interoperability.common.exceptions.HandlerException;
import si.sunesis.interoperability.common.interfaces.RequestHandler;

import java.util.logging.Handler;
import java.util.logging.LogRecord;

/**
 * Abstract base class for Modbus request handlers.
 * Provides common functionality for processing Modbus requests and responses.
 * Extends the common AbstractRequestHandler and implements the RequestHandler interface
 * specifically for Modbus protocol communications.
 *
 * @author David Trafela, Sunesis
 * @since 1.0.0
 */
@Slf4j
public abstract class AbstractModbusRequestHandler extends AbstractRequestHandler<ModbusMaster, ModbusResponse> implements RequestHandler<ModbusRequest, ModbusResponse> {

    /**
     * Constructs a new AbstractModbusRequestHandler with the specified Modbus master.
     * Sets up logging and event listeners for Modbus communication.
     *
     * @param master the Modbus master to use for communication
     */
    @SneakyThrows
    protected AbstractModbusRequestHandler(ModbusMaster master) {
        Modbus.log().addHandler(new Handler() {
            @Override
            public void publish(LogRecord logRecord) {
                log.debug("{}: {}", logRecord.getLevel().getName(), logRecord.getMessage());
            }

            @Override
            public void flush() {
                //do nothing
            }

            @Override
            public void close() throws SecurityException {
                //do nothing
            }
        });
        Modbus.setLogLevel(Modbus.LogLevel.LEVEL_DEBUG);

        this.client = master;

        master.addListener(new FrameEventListener() {
            @Override
            public void frameSentEvent(FrameEvent frameEvent) {
                String[] hexArray = new String[frameEvent.getBytes().length];
                for (int i = 0; i < frameEvent.getBytes().length; i++) {
                    hexArray[i] = String.format("%02X", frameEvent.getBytes()[i]); // Format as uppercase hex
                }
                log.debug("Frame sent: {}", String.join("", hexArray));
            }

            @Override
            public void frameReceivedEvent(FrameEvent frameEvent) {
                String[] hexArray = new String[frameEvent.getBytes().length];
                for (int i = 0; i < frameEvent.getBytes().length; i++) {
                    hexArray[i] = String.format("%02X", frameEvent.getBytes()[i]); // Format as uppercase hex
                }
                log.debug("Frame received: {}", String.join("", hexArray));
            }
        });
    }

    /**
     * Gets the Modbus master client used by this handler.
     *
     * @return the Modbus master client
     */
    @Override
    public ModbusMaster getClient() {
        return this.client;
    }

    /**
     * Sends a Modbus request and handles the response via callback.
     * Implements the request-reply pattern for Modbus communication.
     *
     * @param request the Modbus request to send
     * @param device the device identifier
     * @param callback the callback to handle the response
     * @throws HandlerException if an error occurs during request processing
     */
    @Override
    public void requestReply(ModbusRequest request, String device, Callback<ModbusResponse> callback) throws HandlerException {
        try {
            log.debug("Sending Modbus request to device: {}", device);

            callback.onNext(this.client.processRequest(request));
        } catch (Exception e) {
            throw new HandlerException("Error processing Modbus request", e);
        }
    }

    /**
     * Disconnects the Modbus client and releases resources.
     * Safely closes the connection to the Modbus device.
     */
    @Override
    public void disconnect() {
        if (this.client != null && this.client.isConnected()) {
            try {
                this.client.disconnect();
            } catch (ModbusIOException e) {
                log.error("Error disconnecting Modbus client", e);
            }
        }
    }
}
