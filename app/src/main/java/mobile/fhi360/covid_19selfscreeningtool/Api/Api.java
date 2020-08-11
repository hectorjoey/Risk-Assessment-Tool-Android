package mobile.fhi360.covid_19selfscreeningtool.Api;

import java.util.List;

import mobile.fhi360.covid_19selfscreeningtool.model.UserHealthData;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface Api {
//
//    @FormUrlEncoded
//    @POST("users")
//    Call<Users> createUser(
//            @Field("firstname") String firstname,
//            @Field("lastname") String lastname,
//            @Field("designation") String designation,
//            @Field("email") String email,
//            @Field("password") String password,
//            @Field("userType") String userType,
//            @Field("gender") String gender,
//            @Field("state") String state,
//            @Field("supervisedBy") String supervisedBy);

    @FormUrlEncoded
    @POST("userHealthData")
    Call<UserHealthData> createUserHealthData(
            @Field("fullname") String fullname,
            @Field("date") String date,
            @Field("age") String age,
            @Field("gender") String gender,
            @Field("state") String state,
            @Field("lga") String lga,
            @Field("phone") String phone,
            @Field("feverSymptom") String feverSymptom,
            @Field("coughSymptom") String coughSymptom,
            @Field("difficultyInBreathingSymptom") String difficultyInBreathingSymptom,
            @Field("sneezingSymptoms") String sneezingSymptoms,
            @Field("chestPainSymptoms") String chestPainSymptoms,
            @Field("diarrhoeaSymptoms") String diarrhoeaSymptoms,
            @Field("fluSymptoms") String fluSymptoms,
            @Field("soreThroatSymptoms") String soreThroatSymptoms,
            @Field("lossOfSmellSymptoms") String lossOfSmellSymptoms,
            @Field("lossOfTasteSymptoms") String lossOfTasteSymptoms,
            @Field("contactWithSomeoneWithSymptoms") String contactWithSomeoneWithSymptoms,
            @Field("exposedToFacilityWithConfirmedCase") String exposedToFacilityWithConfirmedCase,
            @Field("contactWithFever") String contactWithFever,
            @Field("contactWithCough") String contactWithCough,
            @Field("contactWithDifficultBreathing") String contactWithDifficultBreathing,
            @Field("contactWithSneeze") String contactWithSneeze,
            @Field("contactWithChestpain") String contactWithChestpain,
            @Field("contactWithDiarrhoea") String contactWithDiarrhoea,
            @Field("contactWithOtherFLu") String contactWithOtherFLu,
            @Field("contactWithSoreThroat") String contactWithSoreThroat,
            @Field("contactWithLossOfSmell") String contactWithLossOfSmell,
            @Field("contactWithLossOfTaste") String contactWithLossOfTaste,
            @Field("underlyingConditions") String underlyingConditions,
            @Field("specifyKidney") String specifyKidney,
            @Field("specifyPregnancy") String specifyPregnancy,
            @Field("specifyTB") String specifyTB,
            @Field("specifyDiabetes") String specifyDiabetes,
            @Field("specifyLiver") String specifyLiver,
            @Field("specifyChronicLungDisease") String specifyChronicLungDisease,
            @Field("specifyCancer") String specifyCancer,
            @Field("specifyHeartDisease") String specifyHeartDisease,
            @Field("specifyHIV") String specifyHIV,
            @Field("treatment") String treatment,
            @Field("enoughDrugsForThreeMonths") String enoughDrugsForThreeMonths,
            @Field("someoneHelpingYouManageHIV") String someoneHelpingYouManageHIV,
            @Field("covid19CareFromSomeoneInHousehold") String covid19CareFromSomeoneInHousehold,
//            @Field("userId") Long userId,
            @Field("risk") String risk
    );

//    //the users login call
//    @Headers({"Accept: application/json"})
//    @FormUrlEncoded
//    @POST("login")
//    Call<Users> login(
//            @Field("email") String email,
//            @Field("password") String password
//    );

//    //fetching all users
//    @GET("users")
//    Call<List<Users>> getUsers();

//    //fetching all user health data
//    @GET("userHealthData")
//    Call<List<UserHealthData>> getUserHealthData();

    //fetching users by Id
//    @GET("users/{id}")
//    Call<List<Users>> getUsersById(@Path("id") Long id);

    //supervisors Login
//    @FormUrlEncoded
//    @POST("supLogin")
//    Call<Supervisor> supLogin(
//            @Field("email") String email,
//            @Field("password") String password
//    );

//    //create supervisor
//    @FormUrlEncoded
//    @POST("supervisor")
//    Call<Supervisor> createSupervisor(
//            @Field("fullname") String fullname,
//            @Field("email") String email,
//            @Field("password") String password,
//            @Field("userType") String userType,
//            @Field("state") String state,
//            @Field("designation") String designation);

//    //fetching users by supervisor Id
//    @GET("user/{supervisorId}")
//    Call<List<Users>> getUsersBySupervisorId(@Path("supervisorId") String supervisorId);

//    //fetching user health data
//    @GET("userHealthData/{userId}")
//    Call<List<UserHealthData>> getUserHealthByUserId(@Path("userId") Long userId);

    //fetching all supervisors
//    @GET("supervisors")
//    Call<List<Supervisor>> getSupervisors();
//
//    @GET("userHealthdata/{supervisorId}")
//    Call<List<UserHealthData>> getUserHealthDataBySupervisorId(@Path("supervisorId") String supervisorId);
}
