package com.example.byblosapplication;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import android.content.Context;

import org.junit.Before;
import org.junit.Test;


public class UnitTests {




    private Users testUser;
    private WorkingHours testWorkHours;

    @Test
    public void userEmailTest_passed() {

        //CreateBranchAccount myObjectUnderTest = new CreateBranchAccount(mMockContext);
        boolean result = CreateBranchAccount.userEmailValidation("testEmail@test.com");
        assertThat(result,is(true));
    }
    @Test
    public void userEmailTest_failed() {
        //CreateBranchAccount myObjectUnderTest = new CreateBranchAccount(mMockContext);
        boolean result = CreateBranchAccount.userEmailValidation("testEmail@email@email.ca");
        assertThat(result,is(false));
    }
    @Test
    public void phoneTest_passed() {
        //CreateBranchAccount myObjectUnderTest = new CreateBranchAccount(mMockContext);
        boolean result = CreateBranchAccount.phoneValidation("613-613-6133");
        assertThat(result,is(true));
    }
    @Test
    public void phoneTest_failed() {
        //CreateBranchAccount myObjectUnderTest = new CreateBranchAccount(mMockContext);
        boolean result = CreateBranchAccount.phoneValidation("613-613-61361");
        assertThat(result,is(false));
    }
    @Test
    public void zipTest_passed() {
        //CreateBranchAccount myObjectUnderTest = new CreateBranchAccount(mMockContext);
        boolean result = CreateBranchAccount.zipValidation("K1N 6N5");
        assertThat(result,is(true));
    }
    @Test
    public void zipTest_failed() {
        //CreateBranchAccount myObjectUnderTest = new CreateBranchAccount(mMockContext);
        boolean result = CreateBranchAccount.zipValidation("K1N N65");
        assertThat(result,is(false));
    }
    @Test
    public void countryTest_Failed() {
        //CreateBranchAccount myObjectUnderTest = new CreateBranchAccount(mMockContext);
        boolean result = CreateBranchAccount.countryValidation("Canada123");
        assertThat(result,is(false));
    }

    @Before
    public void setup() {
        testUser = new Users("admin", null, "admin","admin", null, null, null, null, null, null);
        testWorkHours = new WorkingHours(9,0,5,0);
    }

    @Test
    public void signin_passed() {
        //Users test = new Users("admin",null, "admin", null, null,null,null,null,null,null);
        boolean result = LogInPage.validAccount("admin","admin", testUser);
        assertThat(result,is(true));
    }

    @Test
    public void signin_failed() {
        //Users test = new Users("employee", null,"employee123",null,null,null,null,null,null,null);
        boolean result = LogInPage.validAccount("employee","admin",testUser);
        assertThat(result,is(false));
    }

    @Test
    public void validStartHours_passed() {
        boolean result = EmployeeFeatures.validStartHours(testWorkHours, 5,0);
        assertThat(result, is(true));
    }

    @Test
    public void validStartHours_failed() {
        boolean result = EmployeeFeatures.validStartHours(testWorkHours, 7, 20);
        assertThat(result,is(false));
    }

    @Test
    public void validEndHours_passed() {
        boolean result = EmployeeFeatures.validEndHours(testWorkHours, 10,0);
        assertThat(result, is(true));
    }

    @Test
    public void validEndHours_failed() {
        boolean result = EmployeeFeatures.validEndHours(testWorkHours,2,20);
        assertThat(result, is(false));
    }


}
