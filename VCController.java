import javax.net.ssl.SSLServerSocket;
import javax.net.ssl.SSLServerSocketFactory;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLContext;
import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.TrustManagerFactory;
import java.io.*;
import java.security.KeyStore;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class VCC {
    private static final int PORT = 12345;
    private SSLServerSocket serverSocket;
    private ExecutorService executorService;

    public VCC() {
        try {
            // Load keystore and truststore for SSL/TLS
            char[] keystorePassword = "keystore_password".toCharArray();
            char[] truststorePassword = "truststore_password".toCharArray();
            KeyStore keystore = KeyStore.getInstance("JKS");
            KeyStore truststore = KeyStore.getInstance("JKS");

            keystore.load(new FileInputStream("server_keystore.jks"), keystorePassword);
            truststore.load(new FileInputStream("server_truststore.jks"), truststorePassword);

            // Set up SSL/TLS
            SSLContext sslContext = SSLContext.getInstance("TLS");
            KeyManagerFactory kmf = KeyManagerFactory.getInstance("SunX509");
            kmf.init(keystore, keystorePassword);
            TrustManagerFactory tmf = TrustManagerFactory.getInstance("SunX509");
            tmf.init(truststore);

            sslContext.init(kmf.getKeyManagers(), tmf.getTrustManagers(), null);
            SSLServerSocketFactory ssf = sslContext.getServerSocketFactory();
            serverSocket = (SSLServerSocket) ssf.createServerSocket(PORT);

            executorService = Executors.newCachedThreadPool();
            System.out.println("VCController Server started. Listening on port " + PORT);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void startServer() {
        while (true) {
            try {
                SSLSocket clientSocket = (SSLSocket) serverSocket.accept();
                System.out.println("Client connected: " + clientSocket.getInetAddress().getHostAddress());

                // Create a new thread to handle each client
                executorService.execute(new ClientHandler(clientSocket));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private class ClientHandler implements Runnable {
        private SSLSocket clientSocket;

        public ClientHandler(SSLSocket clientSocket) {
            this.clientSocket = clientSocket;
        }

        @Override
        public void run() {
            try {
                ObjectInputStream objectInputStream = new ObjectInputStream(clientSocket.getInputStream());

                // Receive data from the client
                Object receivedData = objectInputStream.readObject();

                if (receivedData instanceof Client) {
                    handleClientData((Client) receivedData);
                } else if (receivedData instanceof VehicleOwner) {
                    handleVehicleOwnerData((VehicleOwner) receivedData);
                } else {
                    System.out.println("Received unknown data type from the client.");
                }

                objectInputStream.close();
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            } finally {
                try {
                    clientSocket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        private void sendResponse(SSLSocket clientSocket, boolean accepted, String message) {
            try {
                ObjectOutputStream objectOutputStream = new ObjectOutputStream(clientSocket.getOutputStream());
                Response response = new Response(accepted, message);
                objectOutputStream.writeObject(response);
                objectOutputStream.flush();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        private void sendVehicleOwnerResponse(SSLSocket clientSocket, boolean accepted, String message) {
            try {
                ObjectOutputStream objectOutputStream = new ObjectOutputStream(clientSocket.getOutputStream());
                VehicleOwnerResponse response = new VehicleOwnerResponse(accepted, message);
                objectOutputStream.writeObject(response);
                objectOutputStream.flush();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        private void handleClientData(Client client) {
            // Process and authorize client data
            boolean authorized = processAndAuthorizeClientData(client);

            if (authorized) {
                sendResponse(clientSocket, true, "Data accepted");
            } else {
                sendResponse(clientSocket, false, "Data rejected");
            }
        }

        private void handleVehicleOwnerData(VehicleOwner vehicleOwner) {
            // Process and authorize vehicle owner data
            boolean authorized = processAndAuthorizeVehicleOwnerData(vehicleOwner);

            if (authorized) {
                sendVehicleOwnerResponse(clientSocket, true, "Data accepted");
            } else {
                sendVehicleOwnerResponse(clientSocket, false, "Data rejected");
            }
        }

        private boolean processAndAuthorizeClientData(Client client) {
            // Implement your authorization logic here
            return true; // Modify this based on your logic
        }

        private boolean processAndAuthorizeVehicleOwnerData(VehicleOwner vehicleOwner) {
            // Implement your authorization logic here
            return true; // Modify this based on your logic
        }
    }

    public static void main(String[] args) {
        VCC vcController = new VCC();
        vcController.startServer();
    }

    public List<Integer> jobCompletion(int[] jobTimes) {
        List<Integer> jobCompletionTimes = new ArrayList<>();
        int totalTime = 0;
        for (int i : jobTimes) {
            totalTime += i;
            jobCompletionTimes.add(totalTime);
        }
        return jobCompletionTimes;
}

class Client implements Serializable {

	public Client(int client, int jobID, int jobDuration, String deadline) {
		// TODO Auto-generated constructor stub
	}
    // Define the fields and methods for the Client class
    // Implement any necessary logic for your use case
}

class VehicleOwner implements Serializable {

	public VehicleOwner(int vOID, String make, String model, String plate) {
		// TODO Auto-generated constructor stub
	}
    // Define the fields and methods for the VehicleOwner class
    // Implement any necessary logic for your use case
}

class Response implements Serializable {
    private boolean accepted;
    private String message;

    public Response(boolean accepted, String message) {
        this.accepted = accepted;
        this.message = message;
    }

    public boolean isAccepted() {
        return accepted;
    }

    public String getMessage() {
        return message;
    }
}

class VehicleOwnerResponse implements Serializable {
    private boolean accepted;
    private String message;

    public VehicleOwnerResponse(boolean accepted, String message) {
        this.accepted = accepted;
        this.message = message;
    }

    public boolean isAccepted() {
        return accepted;
    }

    public String getMessage() {
        return message;
    }
}}
