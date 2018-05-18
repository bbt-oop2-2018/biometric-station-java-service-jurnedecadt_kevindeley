 
package biometric_station;

import be.vives.oop.mqtt.chatservice.IMqttMessageHandler;
import java.net.URL;
import java.util.ResourceBundle;

public class Biometric_station  implements IMqttMessageHandler  {
 
    public static void main(String[] args) throws Exception {
        MqttBiometricService values = new MqttBiometricService("biometric", "jurnekevin");
        SerialLineReceiver receiver = new SerialLineReceiver(0, 115200, false);
        
        receiver.setLineListener(new SerialPortLineListener() {
            @Override
            public void serialLineEvent(SerialData data) {
                System.out.println("Received data from the serial port: " + data.getDataAsString());
                String receiveddata = data.getDataAsString();
                if (receiveddata.substring(0,1).equals("[") && receiveddata.substring(receiveddata.length()-2,receiveddata.length()-1).equals("]")){
                    String code = receiveddata.substring(1, receiveddata.length()-2);
                    System.out.println(code);
                    values.sendMessage(data.getDataAsString());
                } else {
                    System.out.println("past");
                }
                
            }
        });
    }

    @Override
    public void messageArrived(String channel, String message) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
