package com.inuker.bluetooth.library.connect.request;

import android.bluetooth.BluetoothGatt;
import android.bluetooth.BluetoothGattDescriptor;

import com.inuker.bluetooth.library.connect.listener.WriteDescriptorListener;
import com.inuker.bluetooth.library.connect.response.BluetoothResponse;

import java.util.UUID;

/**
 * Created by dingjikerbo on 2015/11/10.
 */
public class BleUnnotifyRequest extends BleRequest implements WriteDescriptorListener {

    public BleUnnotifyRequest(String mac, UUID service, UUID character, BluetoothResponse response) {
        super(mac, response);
        mServiceUUID = service;
        mCharacterUUID = character;
    }

    @Override
    void processRequest() {
        switch (getConnectStatus()) {
            case STATUS_DEVICE_SERVICE_READY:
                if (setCharacteristicNotification(mServiceUUID, mCharacterUUID, false)) {
                    registerGattResponseListener(this);
                } else {
                    onRequestFinished(REQUEST_FAILED);
                }
                break;

            default:
                onRequestFinished(REQUEST_FAILED);
                break;
        }
    }

    @Override
    int getGattResponseListenerId() {
        return GATT_RESP_DESCRIPTOR_WRITE;
    }

    @Override
    public void onDescriptorWrite(int status, BluetoothGattDescriptor descriptor) {
        if (status == BluetoothGatt.GATT_SUCCESS) {
            onRequestFinished(REQUEST_SUCCESS);
        } else {
            onRequestFinished(REQUEST_FAILED);
        }
    }
}
