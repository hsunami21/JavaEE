package election.web.model;

import java.util.HashMap;
import java.util.Map;

import wendall.stephen.exceptions.StudentNotRecognizedException;

public class StudentBody {
	private static StudentBody electoralRoll = null;
	private Map<String, Student> students = null;

	private StudentBody() throws StudentNotRecognizedException {
		students = new HashMap<String, Student>();
		// simple student IDs and names
		students.put("3001", new Student("3001", "aaa"));
		students.put("3002", new Student("3002", "bbb"));
		students.put("3003", new Student("3003", "ccc"));
		students.put("3004", new Student("3004", "ddd"));
		students.put("3005", new Student("3005", "eee"));
		students.put("3006", new Student("3006", "fff"));
		students.put("3007", new Student("3007", "ggg"));
		students.put("3008", new Student("3008", "hhh"));
		students.put("3009", new Student("3009", "iii"));
		students.put("3010", new Student("3010", "jjj"));
		students.put("3011", new Student("3011", "kkk"));
		students.put("3012", new Student("3012", "lll"));
		students.put("3013", new Student("3013", "mmm"));
		students.put("3014", new Student("3014", "nnn"));
		students.put("3015", new Student("3015", "ooo"));
		students.put("3016", new Student("3016", "ppp"));
		students.put("3017", new Student("3017", "qqq"));
		students.put("3018", new Student("3018", "rrr"));
		students.put("3019", new Student("3019", "sss"));
		students.put("3020", new Student("3020", "ttt"));
		students.put("3021", new Student("3021", "uuu"));
		students.put("3022", new Student("3022", "vvv"));
		students.put("3023", new Student("3023", "www"));
		students.put("3024", new Student("3024", "xxx"));
		students.put("3025", new Student("3025", "yyy"));
		students.put("3026", new Student("3026", "zzz"));
		// finally add the administrator
		students.put("3000", new Student("3000", "admin"));
	}

	public static synchronized StudentBody getInstance() {
		try {
			if (electoralRoll == null) {
				electoralRoll = new StudentBody();
			}
		} catch (StudentNotRecognizedException vnre) {
			// exception should never happen
		}
		return electoralRoll;
	}

	// returns null if no student has this ID
	public Student getStudent(String studentId) {
		return students.get(studentId);
	}

	public boolean authenticate(String studentId, String password) {
		Student student = students.get(studentId);
		if (student == null) {
			return false;
		}
		if (student.getPassword().equals(password)) {
			return true;
		}
		return false;
	}

}
