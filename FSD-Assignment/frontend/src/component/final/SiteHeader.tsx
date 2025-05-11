import Logo from "../../assets/logo.png";
import "bootstrap/dist/css/bootstrap.css";
import "./SiteHeader.css";
import App from "../../App";

function SiteHeader() {

  return (
    <>
      <nav className="navbar navbar-expand-lg bg-body-tertiary site-header">
        <div className="container-fluid">
          <a className="navbar-brand" href="/">
            <img src={Logo} className="img-fluid img-thumbnail site-logo" />
          </a>
          <ul className="navbar-nav me-auto mb-2 mb-lg-0">
            <li className="nav-item">
              <a
                className="nav-link active"
                aria-current="page"
                href="#"
                onClick={App.}
              >
                Student Management
              </a>
            </li>
            <li className="nav-item">
              <a className="nav-link active" aria-current="page" href="#">
                Drive Management
              </a>
            </li>
          </ul>
        </div>
      </nav>
    </>
  );
}

export default SiteHeader;
