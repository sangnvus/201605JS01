package vn.edu.fu.veazy.core.response;

public class StatsUsersResponse {

    private int activeUser;
    private int totalUser;
    
    public StatsUsersResponse(int activeUser, int totalUser) {
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
