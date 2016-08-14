package vn.edu.fu.veazy.core.response;

public class UsersStatsResponse {

    private int activeUser;
    private int totalUser;
    
    public UsersStatsResponse(int activeUser, int totalUser) {
        super();
        this.activeUser = activeUser;
        this.totalUser = totalUser;
    }
    
    public int getActiveUser() {
        return activeUser;
    }
    
    public void setActiveUser(int activeUser) {
        this.activeUser = activeUser;
    }
    
    public int getTotalUser() {
        return totalUser;
    }
    
    public void setTotalUser(int totalUser) {
        this.totalUser = totalUser;
    }

}
