
import React, { Component } from "react";
import { ReactDOM } from "react";
import IPOService from "../services/IPOService";
import Table from "react-bootstrap/Table";

export default class AddIPODetail extends Component {
    constructor(props) {
        super(props);
        this.onChangecompanyName = this.onChangecompanyName.bind(this);
        this.onChangepricePerShare = this.onChangepricePerShare.bind(this);
        this.onChangetotalnumberOfShares = this.onChangetotalnumberOfShares.bind(this);
        this.onChangeopenDateTime = this.onChangeopenDateTime.bind(this);
        this.onChangeremarks = this.onChangeremarks.bind(this);
        this.saveipodetail = this.saveipodetail.bind(this);
        this.newipo = this.newipo.bind(this);
        this.onChangeCompanyDropdown = this.onChangeCompanyDropdown.bind(this);
        this.state = {
            companyName: "",
            pricePerShare: 0,
            totalnumberOfShares: 0,
            openDateTime: "",
            remarks: "",
            submitted: false,
            selectedCompany:"",
            Companies:[],
            ipolist: []
        };
    }
    componentDidMount() {
        IPOService.getAll().then((response => {
            this.setState({ ipolist: response.data })
        }));
        IPOService.getAllCompany().then((response => {
            console.log(response.data);
            this.setState({
                Companies: response.data
            })}
         ));
    }

    onChangecompanyName(e) {
        this.setState({
            companyName: e.target.value
        });
    }

    onChangepricePerShare(e) {
        this.setState({
            pricePerShare: e.target.value
        });
    }
    onChangetotalnumberOfShares(e) {
        this.setState({
            totalnumberOfShares: e.target.value
        });
    }
    onChangeopenDateTime(e) {
        this.setState({
            openDateTime: e.target.value
        });
    }
    onChangeremarks(e) {
        this.setState({
            remarks: e.target.value
        });
    }

    saveipodetail() {
        var data = {
            companyName: this.state.companyName,
            pricePerShare: this.state.pricePerShare,
            totalnumberOfShares: this.state.totalnumberOfShares,
            openDateTime: this.state.openDateTime,
            remarks: this.state.remarks
        };
        console.log(JSON.stringify(data));
        const requestOptions = {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
                'Authorization': 'Bearer my-token',
                'My-Custom-Header': 'frontend'
            },
            body: JSON.stringify(data)
        };
        //fetch('https://stockmarketcharting.herokuapp.com/ipodetails', requestOptions)
        fetch('https://stockmarketcharting-react.herokuapp.com/ipodetails', requestOptions)
            .then(response => {
                this.setState({
                    submitted: true
                });
                console.log(response.data);
            })
            .catch(e => {
                console.log(e);
            });
    }

    newipo() {
        this.setState({
            companyName: "",
            pricePerShare: 0,
            totalnumberOfShares: 0,
            openDateTime: "",
            remarks: "",
            submitted: false
        });
    }
    onChangeCompanyDropdown(e) {
        this.setState({
            selectedCompany: e.target.value,
            companyName: e.target.value,
            //validationError:e.target.value === ""?"You Must select a sector ":""});
        });
    }

    render() {
        return (
            <div>
                <div className="submit-form">
                    {this.state.submitted ? (
                        <div>
                            <h4>You submitted successfully!</h4>
                            <button className="btn btn-success" onClick={this.newipo}>
                                Add
                            </button>
                        </div>
                    ) : (
                        <div>
                            Select Company
                            <select
                                onClick={this.onChangeCompanyDropdown}>
                                {this.state.Companies.map((Company) => <option key={Company.id} value={Company.companyName}> {Company.companyName} </option>)}
                                value={this.state.selectedCompany}
                            </select>

                            <div className="form-group">
                                <label htmlFor="title">Price Per Share</label>
                                <input
                                    type="number"
                                    className="form-control"
                                    id="pricePerShare"
                                    required
                                    value={this.state.pricePerShare}
                                    onChange={this.onChangepricePerShare}
                                    name="pricePerShare"
                                />
                            </div>
                            <div className="form-group">
                                <label htmlFor="title">Total Number of Shares</label>
                                <input
                                    type="number"
                                    className="form-control"
                                    id="totalnumberOfShares"
                                    required
                                    value={this.state.totalnumberOfShares}
                                    onChange={this.onChangetotalnumberOfShares}
                                    name="totalnumberOfShares"
                                />
                            </div>
                            <div className="form-group">
                                <label htmlFor="title">Open Date and Time</label>
                                <input
                                    type="datetime-local"
                                    className="form-control"
                                    id="openDateTime"
                                    required
                                    value={this.state.openDateTime}
                                    onChange={this.onChangeopenDateTime}
                                    name="openDateTime"
                                />
                            </div>
                            <div className="form-group">
                                <label htmlFor="title">Remarks</label>
                                <input
                                    type="text"
                                    className="form-control"
                                    id="remarks"
                                    required
                                    value={this.state.remarks}
                                    onChange={this.onChangeremarks}
                                    name="remarks"
                                />
                            </div>

                            <button onClick={this.saveipodetail} className="btn btn-success">
                                Submit
                            </button>
                        </div>
                    )}
                </div>
                <div>
                    <h1 className="text-center"> IPO Details</h1>
                    <Table className="table table-striped">
                        <thead>
                            <tr>
                                <td>IPO-ID</td>
                                <td>Company Name</td>
                                <td>Open Date Time</td>
                                <td>Price Per Share</td>
                                <td>Total Number Of Shares</td>
                            </tr>
                        </thead>

                        <tbody>
                            {
                                this.state.ipolist.map(
                                    ipolist =>
                                        <tr key={ipolist.id}>
                                            <td>{ipolist.id}</td>
                                            <td>{ipolist.companyName}</td>
                                            <td>{ipolist.openDateTime}</td>
                                            <td>{ipolist.pricePerShare}</td>
                                            <td>{ipolist.totalnumberOfShares}</td>
                                        </tr>
                                )
                            }
                        </tbody>
                    </Table>
                </div>
            </div>

        );
    }
}
