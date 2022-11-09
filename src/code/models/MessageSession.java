package code.models;

import java.util.List;

public class MessageSession extends Message {

    private List<String> addrMembres;

    public MessageSession(String id, List<String> address) {
        super(id);
        this.addrMembres = address;
    }

    public List<String> getAddrMembres() {
        return this.addrMembres;
    }
    
}
