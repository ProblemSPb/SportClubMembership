// subclass of Member
// single membership

public class SingleClassMember extends Member {

    private int club;

    // constructor
    SingleClassMember(char pMemberType, int pMemberID, String pName, double pFees, int pClub) {
        super(pMemberType, pMemberID, pName, pFees);
        club = pClub;
    }

    // getters and setter
    public int getClub() {
        return club;
    }

    public void setClub(int pClub) {
        club = pClub;
    }

    @Override
    public String toString() {
        return super.toString() + ", " + club;
    }
}
