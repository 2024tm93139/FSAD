import { useEffect, useState } from "react";
import StudentComponent from "./StudentComponent";
function MainBodyRightComponent() {
  const [students, setStudents] = useState([]);
  useEffect(() => {
    fetch("http://localhost:8080/student/all")
      .then((res) => res.json())
      .then((data) => setStudents(data));
  }, []);
  return <StudentComponent items={students} />;
}
export default MainBodyRightComponent;
