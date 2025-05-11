import { ChangeEvent, useEffect, useRef, useState } from "react";
import "./StudentComponent.css";
interface StudentComponetnProps {
  items: [];
}
function StudentComponent(props: StudentComponetnProps) {
  const [students, setStudents] = useState([]);
  const [editingId, updateEditingId] = useState(-1);
  const [name, setName] = useState("");
  const [rollNo, setRollno] = useState("");
  const [status, setStatus] = useState("");
  const [id, setID] = useState("");
  useEffect(() => {
    fetch("http://localhost:8080/student/all")
      .then((res) => res.json())
      .then((data) => setStudents(data));
  }, [editingId]);

  if (students.length === 0) {
    return <>No Record found</>;
  }

  async function updateStudent() {
    const response = await fetch("http://localhost:8080/student/update", {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
      },
      body: JSON.stringify({
        id,
        name,
        rollNo,
        status,
      }),
    });
    if (response.status > 0) {
      setName("");
      setRollno("");
      setStatus("");
      setID("");
      updateEditingId(-1);
    }
  }

  function SubmitForm() {
    updateStudent();
  }

  return (
    <>
      <div className="row">
        {students.map((student) => (
          <>
            <div
              className="card student_card"
              id={student.id}
              key={student.id}
              hidden={editingId === student.id ? true : false}
            >
              <div className="card-body">
                <h5 className="card-title">Name : {student.name}</h5>
                <p className="card-text">
                  Roll No : {student.rollNo}
                  <br />
                  Vaccinated : {student.vaccinated ? "Yes" : "No"}
                </p>

                <button
                  type="button"
                  className="btn btn-primary"
                  onClick={() => updateEditingId(student.id)}
                >
                  Edit
                </button>
              </div>
            </div>

            {/* Edit form */}
            <div
              className="card student_card"
              key={student.id + "edit"}
              id={student.id + "edit"}
              hidden={editingId === student.id ? false : true}
            >
              <form className="student_edit_form" id="student_edit_form">
                <input
                  type="text"
                  className="form-control"
                  onChange={(event) => (
                    setName(event.target.value), setID(student.id)
                  )}
                />
                <input
                  type="text"
                  className="form-control"
                  onChange={(event) => (
                    setRollno(event.target.value), setID(student.id)
                  )}
                />
                <select
                  className="form-select"
                  onChange={(event) => (
                    setStatus(event.target.value), setID(student.id)
                  )}
                >
                  <option value="">Select</option>
                  <option value="true">Yes</option>
                  <option value="false">No</option>
                </select>
                <button
                  type="button"
                  className="btn btn-primary"
                  onClick={SubmitForm}
                >
                  Submit
                </button>
              </form>
            </div>
          </>
        ))}
      </div>
    </>
  );
}
export default StudentComponent;
