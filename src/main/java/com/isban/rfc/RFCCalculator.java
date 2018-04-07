package com.isban.rfc;

/**
 * Use Rfc.Builder to build instances of Rfc.
 */
public class RFCCalculator{

    private String tenDigitsCode;
    private String homoclave;
    private String verificationDigit;

    private RFCCalculator(String tenDigitsCode, String homoclave, String verificationDigit) {

        this.tenDigitsCode = tenDigitsCode;
        this.homoclave = homoclave;
        this.verificationDigit = verificationDigit;
    }

    @Override
    public String toString() {

        return tenDigitsCode + homoclave + verificationDigit;
    }

    public static class Builder {

        private String name;
        private String firstLastName;
        private String secondLastName;
        private int day;
        private int month;
        private int year;

        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public Builder firstLastName(String firstLastName) {
            this.firstLastName = firstLastName;
            return this;
        }

        public Builder secondLastName(String secondLastName) {
            this.secondLastName = secondLastName;
            return this;
        }

        public Builder birthday(int day, int month, int year) {
            this.day = day;
            this.month = month;
            this.year = year;
            return this;
        }

        public Builder creationDate(int day, int month, int year) {
            return birthday(day, month, year);
        }

        public RFCCalculator build() {
                return buildForNaturalPerson();
        }

        private RFCCalculator buildForNaturalPerson() {

            NaturalPerson person = new NaturalPerson(name, firstLastName, secondLastName, day, month, year);

            String tenDigitsCode = new NaturalPersonTenDigitsCodeCalculator(person).calculate();
            String homoclave = new HomoclaveCalculator(person).calculate();
            String verificationDigit = new VerificationDigitCalculator(tenDigitsCode + homoclave).calculate();

            return new RFCCalculator(tenDigitsCode, homoclave, verificationDigit);
        }
    }
}
