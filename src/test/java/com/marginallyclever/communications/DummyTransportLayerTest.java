package com.marginallyclever.communications;

import com.marginallyclever.communications.dummy.DummyTransportLayer;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class DummyTransportLayerTest {

    @Test
    public void testOpenConnection() {
        // Test if a session can be opened successfully
        DummyTransportLayer transportLayer = new DummyTransportLayer();
        Configuration configuration = new Configuration(transportLayer, "testConnection"); // Pass correct arguments
        NetworkSession session = transportLayer.openConnection(configuration);

        // Check if the session is successfully opened
        assertNotNull(session);
        // Check if the returned session name is correct
        assertEquals("dummy", session.getName());
    }

    @Test
    public void testCloseConnection() {
        // Test if the connection can be closed successfully
        DummyTransportLayer transportLayer = new DummyTransportLayer();
        NetworkSession session = transportLayer.openConnection(new Configuration(transportLayer, "testConnection"));
        session.closeConnection();

        // Check if the session is successfully closed
        assertFalse(session.isOpen());
    }

    @Test
    public void testSendMessage() throws Exception {
        // Test if sending a message is handled correctly
        DummyTransportLayer transportLayer = new DummyTransportLayer();
        NetworkSession session = transportLayer.openConnection(new Configuration(transportLayer, "testConnection"));
        session.sendMessage("test message");

        // Assuming we have a mechanism to verify that the message was sent successfully or "ok" was received
        // For now, we are just checking that no exceptions are thrown
    }

    @Test
    public void testListConnections() {
        // Test if the list of connections is returned correctly
        DummyTransportLayer transportLayer = new DummyTransportLayer();
        List<String> connections = transportLayer.listConnections();

        // Check if the correct number of connections is returned
        assertEquals(1, connections.size());
        // Check if the connection name is correct
        assertEquals("dummy", connections.get(0));
    }
}
