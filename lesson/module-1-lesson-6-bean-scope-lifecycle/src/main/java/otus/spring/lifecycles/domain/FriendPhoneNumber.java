package otus.spring.lifecycles.domain;

public class FriendPhoneNumber extends PhoneNumber {
    @Override
    public String getOwnerName() {
        return "Друг";
    }
}
