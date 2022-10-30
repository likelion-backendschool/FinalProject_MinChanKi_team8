package likelion.finalproject.market.member.domain;

public enum Auth {
    GENERAL(3),
    WRITER(4),
    MANAGER(7);

    private final int authLevel;
    Auth(int authLevel) {
        this.authLevel = authLevel;
    }
    public int getAuthLevel() {
        return authLevel;
    }
}