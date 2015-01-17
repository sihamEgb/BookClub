package technion.bookclub;

public interface HomePageInterface {
	public String getUserId();
	public byte[] getMyClubsResponse();
	public void setMyClubsResponse(byte[] r);
}
