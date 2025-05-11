function StudentDetails() {
  var data;
  async () => {
    try {
      const response = await fetch("http://localhost:8080/student/all");
      data = await response.json();
    } catch (error) {
      console.error("Error fetching data:", error);
    }
  };
  return <div>{data}</div>;
}

export default StudentDetails;
