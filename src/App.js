import React, { Component } from "react";
import { Switch, Route, Link } from "react-router-dom";
import "bootstrap/dist/css/bootstrap.min.css";
import "./App.css";

import excelimport from "./components/excelimport";
import AddIPODetail from "./components/ipodetails";
import StockExchange from "./components/StockExchange";
import AddCompany from "./components/addcompany";
import StockExchangeMap from "./components/stockexchangemap";
import Company from "./components/company";
import ManageSector from "./components/ManageSector";

class App extends Component {
  render() {
    return (
      <div>
        <nav className="navbar navbar-expand navbar-dark bg-dark">
          <a href="/importdata" className="navbar-brand">
            Admin
          </a>
          <div className="navbar-nav mr-auto">
            <li className="nav-item">
              <Link to={"/importdata"} className="nav-link">
                Import Data
              </Link>
            </li>
            <li className="nav-item">
              <Link to={"/company"} className="nav-link">
                Manage Company
              </Link>
            </li>
            <li className="nav-item">
              <Link to={"/stockexchange"} className="nav-link">
                Manage Stock Exchanges
              </Link>
            </li>
            <li className="nav-item">
              <Link to={"/ipodetail"} className="nav-link">
                Update IPO Details
              </Link>
            </li>
            <li className="nav-item">
              <Link to={"/mapcompany"} className="nav-link">
                Add Company to Stock Exchange
              </Link>
            </li>
            <li className="nav-item">
              <Link to={"/addsector"} className="nav-link">
                Manage Sector
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
            <Route exact path={["/", "/importdata"]} component={excelimport} />
            <Route exact path="/company" component={AddCompany} />
            <Route exact path="/stockexchange" component={StockExchange} />
            <Route exact path="/ipodetail" component={AddIPODetail} />
            <Route exact path="/mapcompany" component={StockExchangeMap} />
            <Route exact path="/addsector" component={ManageSector} />
            <Route path="/company/:id" component={Company} />
          </Switch>
        </div>
      </div>
    );
  }
}

export default App;