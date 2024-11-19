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

import com.intelligt.modbus.jlibmodbus.exception.ModbusIOException;
import com.intelligt.modbus.jlibmodbus.master.ModbusMaster;
import com.intelligt.modbus.jlibmodbus.msg.base.ModbusRequest;
import com.intelligt.modbus.jlibmodbus.msg.base.ModbusResponse;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import si.sunesis.interoperability.common.AbstractRequestHandler;
import si.sunesis.interoperability.common.exceptions.HandlerException;
import si.sunesis.interoperability.common.interfaces.RequestHandler;

/**
 * @author David Trafela, Sunesis
 * @since 1.0.0
 */
@Slf4j
public abstract class AbstractModbusRequestHandler extends AbstractRequestHandler<ModbusMaster, ModbusResponse> implements RequestHandler<ModbusRequest, ModbusResponse> {

    @SneakyThrows
    protected AbstractModbusRequestHandler(ModbusMaster master) {
        this.client = master;
    }

    @Override
    public ModbusMaster getClient() {
        return this.client;
    }

    @Override
    public void requestReply(ModbusRequest request, String device, Callback<ModbusResponse> callback) throws HandlerException {
        try {
            log.debug("Sending Modbus request to device: {}", device);

            if (!this.client.isConnected()) {
                this.client.connect();
            }

            callback.onNext(this.client.processRequest(request));
        } catch (Exception e) {
            throw new HandlerException("Error processing Modbus request", e);
        }
    }

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
