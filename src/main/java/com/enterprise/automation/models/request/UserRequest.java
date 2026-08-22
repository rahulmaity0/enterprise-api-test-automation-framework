package com.enterprise.automation.models.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserRequest {
    private String name;
    private String username;
    private String email;
    private String phone;
    private String website;
    private Address address;
    private Company company;

    public UserRequest() {}

    public UserRequest(String name, String username, String email, String phone, String website, Address address, Company company) {
        this.name = name;
        this.username = username;
        this.email = email;
        this.phone = phone;
        this.website = website;
        this.address = address;
        this.company = company;
    }

    public static Builder builder() { return new Builder(); }

    public static class Builder {
        private String name;
        private String username;
        private String email;
        private String phone;
        private String website;
        private Address address;
        private Company company;

        public Builder name(String name) { this.name = name; return this; }
        public Builder username(String username) { this.username = username; return this; }
        public Builder email(String email) { this.email = email; return this; }
        public Builder phone(String phone) { this.phone = phone; return this; }
        public Builder website(String website) { this.website = website; return this; }
        public Builder address(Address address) { this.address = address; return this; }
        public Builder company(Company company) { this.company = company; return this; }

        public UserRequest build() {
            return new UserRequest(name, username, email, phone, website, address, company);
        }
    }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }
    public String getWebsite() { return website; }
    public void setWebsite(String website) { this.website = website; }
    public Address getAddress() { return address; }
    public void setAddress(Address address) { this.address = address; }
    public Company getCompany() { return company; }
    public void setCompany(Company company) { this.company = company; }

    @JsonInclude(JsonInclude.Include.NON_NULL)
    public static class Address {
        private String street;
        private String suite;
        private String city;
        private String zipcode;
        private Geo geo;

        public Address() {}
        public Address(String street, String suite, String city, String zipcode, Geo geo) {
            this.street = street;
            this.suite = suite;
            this.city = city;
            this.zipcode = zipcode;
            this.geo = geo;
        }

        public static Builder builder() { return new Builder(); }
        public static class Builder {
            private String street;
            private String suite;
            private String city;
            private String zipcode;
            private Geo geo;
            public Builder street(String street) { this.street = street; return this; }
            public Builder suite(String suite) { this.suite = suite; return this; }
            public Builder city(String city) { this.city = city; return this; }
            public Builder zipcode(String zipcode) { this.zipcode = zipcode; return this; }
            public Builder geo(Geo geo) { this.geo = geo; return this; }
            public Address build() { return new Address(street, suite, city, zipcode, geo); }
        }

        public String getStreet() { return street; }
        public void setStreet(String street) { this.street = street; }
        public String getSuite() { return suite; }
        public void setSuite(String suite) { this.suite = suite; }
        public String getCity() { return city; }
        public void setCity(String city) { this.city = city; }
        public String getZipcode() { return zipcode; }
        public void setZipcode(String zipcode) { this.zipcode = zipcode; }
        public Geo getGeo() { return geo; }
        public void setGeo(Geo geo) { this.geo = geo; }
    }

    @JsonInclude(JsonInclude.Include.NON_NULL)
    public static class Geo {
        private String lat;
        private String lng;

        public Geo() {}
        public Geo(String lat, String lng) { this.lat = lat; this.lng = lng; }

        public static Builder builder() { return new Builder(); }
        public static class Builder {
            private String lat;
            private String lng;
            public Builder lat(String lat) { this.lat = lat; return this; }
            public Builder lng(String lng) { this.lng = lng; return this; }
            public Geo build() { return new Geo(lat, lng); }
        }

        public String getLat() { return lat; }
        public void setLat(String lat) { this.lat = lat; }
        public String getLng() { return lng; }
        public void setLng(String lng) { this.lng = lng; }
    }

    @JsonInclude(JsonInclude.Include.NON_NULL)
    public static class Company {
        private String name;
        private String catchPhrase;
        private String bs;

        public Company() {}
        public Company(String name, String catchPhrase, String bs) {
            this.name = name;
            this.catchPhrase = catchPhrase;
            this.bs = bs;
        }

        public static Builder builder() { return new Builder(); }
        public static class Builder {
            private String name;
            private String catchPhrase;
            private String bs;
            public Builder name(String name) { this.name = name; return this; }
            public Builder catchPhrase(String catchPhrase) { this.catchPhrase = catchPhrase; return this; }
            public Builder bs(String bs) { this.bs = bs; return this; }
            public Company build() { return new Company(name, catchPhrase, bs); }
        }

        public String getName() { return name; }
        public void setName(String name) { this.name = name; }
        public String getCatchPhrase() { return catchPhrase; }
        public void setCatchPhrase(String catchPhrase) { this.catchPhrase = catchPhrase; }
        public String getBs() { return bs; }
        public void setBs(String bs) { this.bs = bs; }
    }
}
