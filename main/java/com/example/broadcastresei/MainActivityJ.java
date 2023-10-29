package com.example.broadcastresei;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.wifi.WifiManager;
import android.os.BatteryManager;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivityJ extends BroadcastReceiver {
    private TextView batteryLevelTextView;
    private TextView airplaneModeTextView;
    private ImageView airplaneModeImageView;
    private TextView wifiStatusTextView;

    public MainActivityJ(TextView batteryLevelTextView, TextView airplaneModeTextView,
                               ImageView airplaneModeImageView, TextView wifiStatusTextView) {
        this.batteryLevelTextView = batteryLevelTextView;
        this.airplaneModeTextView = airplaneModeTextView;
        this.airplaneModeImageView = airplaneModeImageView;
        this.wifiStatusTextView = wifiStatusTextView;
    }
    @Override
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();

        if (action != null) {
            switch (action) {
                case Intent.ACTION_BATTERY_CHANGED:
                    int batteryLevel = intent.getIntExtra(BatteryManager.EXTRA_LEVEL, -1);
                    batteryLevelTextView.setText("Porcentaje de Batería: " + batteryLevel + "%");
                    break;

                case Intent.ACTION_AIRPLANE_MODE_CHANGED:
                    boolean isAirplaneModeOn = intent.getBooleanExtra("state", false);

                    if (isAirplaneModeOn) {
                        airplaneModeTextView.setText("Modo Avión: Activado");
                        airplaneModeImageView.setImageResource(R.drawable.ic_airplanemode_activate);
                    } else {
                        airplaneModeTextView.setText("Modo Avión: Desactivado");
                        airplaneModeImageView.setImageResource(R.drawable.ic_airplanemode_desactivate);
                    }
                    break;

                case WifiManager.WIFI_STATE_CHANGED_ACTION:
                    int wifiState = intent.getIntExtra(WifiManager.EXTRA_WIFI_STATE, -1);

                    switch (wifiState) {
                        case WifiManager.WIFI_STATE_ENABLED:
                            wifiStatusTextView.setText("WiFi: Activado");
                            break;
                        case WifiManager.WIFI_STATE_DISABLED:
                            wifiStatusTextView.setText("WiFi: Desactivado");
                            break;
                        default:
                            wifiStatusTextView.setText("Estado WiFi desconocido");
                            break;
                    }
                    break;
            }
        }
    }
}
