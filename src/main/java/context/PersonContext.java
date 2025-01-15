package context;



/*
* @Project Name: playwright-automation-framework
* @Author: Okechukwu Bright Onwumere
* @Created: 14-Jan-25
*/


public class PersonContext {
    private final ThreadLocal<String> randomFirstName = ThreadLocal.withInitial(() -> null);
    private final ThreadLocal<String> randomLastName = ThreadLocal.withInitial(() -> null);
    private final ThreadLocal<String> randomEmailAddress = ThreadLocal.withInitial(() -> null);

    public String getRandomFirstName() {
        return randomFirstName.get();
    }

    public String getRandomLastName() {
        return randomLastName.get();
    }

    public String getRandomEmailAddress() {
        return randomEmailAddress.get();
    }

    public void setRandomFirstName(String randomFirstName) {
        this.randomFirstName.set(randomFirstName);
    }

    public void setRandomLastName(String randomLastName) {
        this.randomLastName.set(randomLastName);
    }

    public void setRandomEmailAddress(String randomEmailAddress) {
        this.randomEmailAddress.set(randomEmailAddress);
    }
}
