import React, { Component } from "react";
import { Switch, Route, Link } from "react-router-dom";
import "bootstrap/dist/css/bootstrap.min.css";
import "./App.css";
import CompareCompany from "./components/CompareCompany";
import Compare2Companies from "./components/Comparetwocompany";
import CompareSector from "./components/CompareSector";
import UpcomingIPO from "./components/UpcomingIPO";


class UserLandingPage extends Component {
  render() {
    return (
      <div>
        <nav className="navbar navbar-expand navbar-dark bg-dark">
          <a href="/upcomingipo" className="navbar-brand">
            Welcome
          </a>
          <div className="navbar-nav mr-auto">
            <li className="nav-item">
              <Link to={"/upcomingipo"} className="nav-link">
                IPOs
              </Link>
            </li>
            <li className="nav-item">
              <Link to={"/comparecompany"} className="nav-link">
                Compare Company
              </Link>
            </li>
            <li className="nav-item">
              <Link to={"/compare2companies"} className="nav-link">
                Compare 2 Companies
              </Link>
            </li>
            <li className="nav-item">
              <Link to={"/comparesector"} className="nav-link">
                Compare Sector
              </Link>
            </li>
            <li className="nav-item">
              <Link to={"/sign-in"} className="nav-link">
                Logout
              </Link>
            </li>
          </div>
        </nav>

        <div className="container mt-3">
          <Switch>
            <Route exact path="/upcomingipo" component={UpcomingIPO} />
            <Route exact path="/comparecompany" component={CompareCompany} />
            <Route exact path ="/compare2companies" component={Compare2Companies}/>
            <Route exact path="/comparesector" component={CompareSector} />
          </Switch>
        </div>
      </div>
    );
  }
}

export default UserLandingPage;