public class MVCPatternDemo {
  public static void main(String[] args) {       //fetch student record based on his roll no from the database
    Course model  = retriveCourseFromDatabase();       //Create a view : to write course details on console
    CourseView view = new CourseView();      
    CourseController controller = new CourseController(model, view);
    controller.updateView();     //update model data
    controller.setCourseName("Python");
    System.out.println("nAfter updating, Course Details are as follows");
    controller.updateView();   
  }     
  private static Course retriveCourseFromDatabase(){
    Course course = new Course();
    course.setName("Java");
    course.setId("01");
    course.setCategory("Programming");
    return course;
  }
}
