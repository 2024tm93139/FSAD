import { useState } from "react";

function AddstudentComponent() {
  const [name, setName] = useState("");
  const [rollNo, setRollNo] = useState("");
  const [vaccinated, setVaccinated] = useState(false);
  const [file, setFile] = useState(null);
  const [user_status, setUserStatus] = useState("");
  const [file_status, setFileStatus] = useState("");
  function handleSubmit() {
    console.log(JSON.stringify({ name, rollNo, vaccinated }));
    createStudent();
  }

  async function createStudent() {
    const response = await fetch("http://localhost:8080/student/create", {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
      },
      body: JSON.stringify({ name, rollNo, vaccinated }),
    });

    if (response.status === 500) {
      setUserStatus(
        "Something went wrong while creating student. try again later"
      );
    }
    if (response.status === 200) {
      setUserStatus("Student successfully created.");
    }
    const data = await response.json();
    console.log(data);
  }

  async function uploadFile() {
    const response = await fetch("http://localhost:8080/student/file", {
      method: "POST",
      headers: {
        "Content-Type": "application/csv",
      },
      body: file,
    });

    if (response.status === 500) {
      setFileStatus(
        "Something went wrong while uploading file. Try again later"
      );
    }
    if (response.status === 200) {
      setFileStatus("File successfully uploaded.");
    }
  }

  function handleUpload() {
    console.log(file);
    uploadFile();
  }

  return (
    <>
      <div className="row">
        <p></p>
        <p>{user_status}</p>
        <div className="col-6">
          <form>
            <div className="mb-3">
              <label htmlFor="student_name" className="form-label">
                Student Name
              </label>
              <input
                type="email"
                className="form-control"
                id="student_name"
                aria-describedby="emailHelp"
                onChange={(event) => setName(event.target.value)}
              />
            </div>
            <div className="mb-3">
              <label htmlFor="student_roll_no" className="form-label">
                Roll Number
              </label>
              <input
                type="text"
                className="form-control"
                id="student_roll_no"
                onChange={(event) => setRollNo(event.target.value)}
              />
            </div>
            <div className="mb-3 form-check">
              <input
                type="checkbox"
                className="form-check-input"
                id="student_vaccinated"
                onChange={() => setVaccinated(!vaccinated)}
              />
              <label className="form-check-label" htmlFor="student_vaccinated">
                Vaccinated
              </label>
            </div>
            <button
              type="button"
              className="btn btn-primary"
              onClick={handleSubmit}
            >
              Submit
            </button>
          </form>
        </div>

        <div className="col-6">
          <p></p>
          <p>{file_status}</p>
          <form>
            <div className="mb-3">
              <label htmlFor="formFile" className="form-label">
                CSV file for bulk import in <em>(name, rollNo, vaccinated)</em>{" "}
                format
              </label>
              <input
                className="form-control"
                type="file"
                id="formFile"
                onChange={(event) => setFile(event.target.files[0])}
              />
            </div>
            <button
              type="button"
              className="btn btn-primary"
              onClick={handleUpload}
            >
              Submit
            </button>
          </form>
        </div>
      </div>
    </>
  );
}

export default AddstudentComponent;
