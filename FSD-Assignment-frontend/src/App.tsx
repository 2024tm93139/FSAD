import { useState } from "react";
import "./App.css";
import UpcomingDrive from "./component/final/UpcomingDrive";
import ManageStudentComponent from "./component/final/ManageStudentComponent";
import AddstudentComponent from "./component/final/AddStudentComponent";
import ManageDriveComponent from "./component/final/ManageDriveComponent";

function App() {
  const [isLoggedIn, setLoggedIn] = useState(false);
  const [username, setUsername] = useState("");
  const [activeComponent, setActiveComponent] = useState("studentManagement");

  function handleAddStudent() {
    console.log;
    setActiveComponent("addStudent");
  }
  function handleStudentManagement() {
    setActiveComponent("studentManagement");
  }
  function handleDriveManagement() {
    setActiveComponent("driveManagement");
  }

  function handleLogin() {
    setLoggedIn(true);
    console.log(username);
  }

  function getMainBodyComponent(activeComponent: string) {
    if (activeComponent === "addStudent") {
      return <AddstudentComponent />;
    }
    if (activeComponent === "driveManagement") {
      return <ManageDriveComponent />;
    }
    return <ManageStudentComponent />;
  }

  if (!isLoggedIn) {
    return (
      <>
        <form>
          <div className="mb-3">
            <label htmlFor="username" className="form-label">
              Username
            </label>
            <input
              type="email"
              className="form-control"
              id="username"
              aria-describedby="emailHelp"
              onChange={(e) => setUsername(e.target.value)}
            />
          </div>
          <div className="mb-3">
            <label htmlFor="password" className="form-label">
              Password
            </label>
            <input type="password" className="form-control" id="password" />
          </div>
          <button
            type="submit"
            className="btn btn-primary"
            onClick={handleLogin}
          >
            Submit
          </button>
        </form>
      </>
    );
  }
  return (
    <>
      <div className="navbar main-navigation">
        <div className="navbar-brand">Scholar Academy</div>
        <div className="navbar-nav main-navigation-child d-flex flex-row">
          <div
            className="nav-item main-navigation-item"
            onClick={handleAddStudent}
          >
            <a className="nav-link active " aria-current="page" href="#">
              Add Student
            </a>
          </div>
          <div
            className="nav-item main-navigation-item"
            onClick={handleStudentManagement}
          >
            <a className="nav-link active " aria-current="page" href="#">
              Manage Student
            </a>
          </div>
          <div
            className="nav-item main-navigation-item"
            onClick={handleDriveManagement}
          >
            <a className="nav-link active" aria-current="page" href="#">
              Manage Drive
            </a>
          </div>
        </div>
      </div>
      <div className="main-body row">
        <div className="main-body-left">
          <UpcomingDrive />
        </div>
        <div className="main-body-right">
          {getMainBodyComponent(activeComponent)}
        </div>
      </div>
    </>
  );
}

export default App;
