package com.inuker.bluetooth.library.connect;

import com.inuker.bluetooth.library.IBluetoothBase;
import com.inuker.bluetooth.library.connect.options.BleConnectOption;
import com.inuker.bluetooth.library.connect.response.BluetoothResponse;

import java.util.UUID;

/**
 * Created by dingjikerbo on 2016/8/24.
 */
public interface IBleConnectMaster extends IBluetoothBase {

    void connect(BleConnectOption options, BluetoothResponse response);

    void disconnect();

    void read(UUID service, UUID character, BluetoothResponse response);

    void write(UUID service, UUID character, byte[] bytes, BluetoothResponse response);

    void writeNoRsp(UUID service, UUID character, byte[] bytes, BluetoothResponse response);

    void notify(UUID service, UUID character, BluetoothResponse response);

    void unnotify(UUID service, UUID character, BluetoothResponse response);

    void readRssi(BluetoothResponse response);

    void refresh();
}
