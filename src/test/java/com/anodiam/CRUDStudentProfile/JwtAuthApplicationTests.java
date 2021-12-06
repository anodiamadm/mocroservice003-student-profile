package com.anodiam.CRUDStudentProfile;

import com.anodiam.CRUDStudentProfile.model.StudentProfile;
import com.anodiam.CRUDStudentProfile.model.User;
import com.anodiam.CRUDStudentProfile.serviceRepository.Message.MessageService;
import com.anodiam.CRUDStudentProfile.serviceRepository.userProfile.studentProfile.StudentProfileService;
import com.anodiam.CRUDStudentProfile.serviceRepository.userProfile.user.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;


@SpringBootTest
class JwtAuthApplicationTests {

	@Autowired
	private StudentProfileService studentProfileService;

	@Autowired
	private MessageService messageService;

	@Autowired
	private UserService userService;

	int language_Id=CRUDStudentProfileApplication.languageId;

	@Test
	void contextLoads() {
	}

	//	Use Case 1.1: If Student Profile Id not zero, then new student profile won't be added.
	@Test
	public void testNegativeNewStudentProfileIDNotZero() throws Exception
	{
		StudentProfile testStudentProfile=new StudentProfile();
		testStudentProfile.setStudentProfileId(BigInteger.valueOf(-1));
		testStudentProfile.setFullName("Vicky");
		StudentProfile newStudentProfile=studentProfileService.save(testStudentProfile);
		String returnMessage=messageService.showMessage(language_Id,"STUDENT_PROFILE_ID_BLANK");
		assertEquals(newStudentProfile.getMessageResponse().getMessage(),returnMessage);
	}

	//	Use Case 1.2: If Student Profile Id not zero, then new student profile won't be added.
	@Test
	public void testNegativeExistingStudentProfileIDZeroOrLessThanZero() throws Exception
	{
		StudentProfile testStudentProfile=new StudentProfile();
		testStudentProfile.setStudentProfileId(BigInteger.valueOf(0));
		testStudentProfile.setFullName("Vicky");
		StudentProfile newStudentProfile=studentProfileService.modify(testStudentProfile);
		String returnMessage=messageService.showMessage(language_Id,"STUDENT_PROFILE_ID_INVALID");
		assertEquals(newStudentProfile.getMessageResponse().getMessage(),returnMessage);
	}

	//	Use Case 1.3: If Language is null, then new student profile won't be added.
	@Test
	public void testNegativeLanguageNullInAddMode() throws Exception
	{
		StudentProfile testStudentProfile=new StudentProfile();
		testStudentProfile.setStudentProfileId(BigInteger.valueOf(0));
		testStudentProfile.setFullName("Vicky");
		StudentProfile newStudentProfile=studentProfileService.save(testStudentProfile);
		String returnMessage=messageService.showMessage(language_Id,"STUDENT_LANGUAGE_ID_BLANK");
		assertEquals(newStudentProfile.getMessageResponse().getMessage(),returnMessage);
	}

	//	Use Case 1.4: If Language is null, then existing student profile won't be modified.
	@Test
	public void testNegativeLanguageNullInModifyMode() throws Exception
	{
		StudentProfile testStudentProfile=new StudentProfile();
		testStudentProfile.setStudentProfileId(BigInteger.valueOf(1));
		testStudentProfile.setFullName("Vicky");
		User existingUser=userService.GetSingleUser();
		testStudentProfile.setUser(existingUser);
		StudentProfile newStudentProfile=studentProfileService.modify(testStudentProfile);
		String returnMessage=messageService.showMessage(language_Id,"STUDENT_LANGUAGE_ID_BLANK");
		assertEquals(newStudentProfile.getMessageResponse().getMessage(),returnMessage);
	}

	//	Use Case 3.4.1: If email is invalid, =>
	//1. Does not contain exactly one '@' character.
	//2. Does not contain one or more '.' characters after the '@' character.
	//3. Does not contain any alphabet (a-z, A-Z) before '@'.
	//4. Does not contain any alphabet (a-z, A-Z) in between '@' and '.'.
	//5. Does not contain any alphabet (a-z, A-Z) after the last '.' character.
	// I should NOT be able to register. with the following message:
	//	"Student registration failure! Invalid email address."
	@Test
	public void testNegativeInvalidGurdianEmail1() throws Exception
	{
		StudentProfile testStudentProfile=new StudentProfile();
		testStudentProfile.setStudentProfileId(BigInteger.valueOf(0));
		testStudentProfile.setFullName("Vicky");
		testStudentProfile.setGuardiansEmail("vicky.ghosh695gmail.com");
		StudentProfile newStudentProfile=studentProfileService.save(testStudentProfile);
		String returnMessage = messageService.showMessage(language_Id, "GUARDIAN_INVALID_EMAIL_ADDRESS");
		assertEquals(testStudentProfile.getMessageResponse().getMessage(),returnMessage);
	}

	@Test
	public void testNegativeInvalidGurdianEmail2() throws Exception
	{
		StudentProfile testStudentProfile=new StudentProfile();
		testStudentProfile.setStudentProfileId(BigInteger.valueOf(0));
		testStudentProfile.setFullName("Vicky");
		testStudentProfile.setGuardiansEmail("rani.ghosh695@");
		StudentProfile newStudentProfile=studentProfileService.save(testStudentProfile);
		String returnMessage = messageService.showMessage(language_Id, "GUARDIAN_INVALID_EMAIL_ADDRESS");
		assertEquals(testStudentProfile.getMessageResponse().getMessage(),returnMessage);
	}

	@Test
	public void testNegativeInvalidEmail3() throws Exception
	{
		StudentProfile testStudentProfile=new StudentProfile();
		testStudentProfile.setStudentProfileId(BigInteger.valueOf(0));
		testStudentProfile.setFullName("Vicky");
		testStudentProfile.setGuardiansEmail("@gmail.com");
		StudentProfile newStudentProfile=studentProfileService.save(testStudentProfile);
		String returnMessage = messageService.showMessage(language_Id, "GUARDIAN_INVALID_EMAIL_ADDRESS");
		assertEquals(testStudentProfile.getMessageResponse().getMessage(),returnMessage);
	}

	@Test
	public void testNegativeInvalidEmail4() throws Exception
	{
		StudentProfile testStudentProfile=new StudentProfile();
		testStudentProfile.setStudentProfileId(BigInteger.valueOf(0));
		testStudentProfile.setFullName("Vicky");
		testStudentProfile.setGuardiansEmail("vicky.ghosh695@gmail");
		StudentProfile newStudentProfile=studentProfileService.save(testStudentProfile);
		String returnMessage = messageService.showMessage(language_Id, "GUARDIAN_INVALID_EMAIL_ADDRESS");
		assertEquals(testStudentProfile.getMessageResponse().getMessage(),returnMessage);
	}
}
