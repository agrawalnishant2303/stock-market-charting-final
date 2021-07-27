import React, { Component } from "react";
import { Link } from "react-router-dom";
import IPOService from "../services/IPOService";
import Table from "react-bootstrap/esm/Table";
export default class AddCompany extends Component {
    constructor(props) {
        super(props);
        this.onChangecompanyName = this.onChangecompanyName.bind(this);
        this.onChangeturnover = this.onChangeturnover.bind(this);
        this.onChangeceo = this.onChangeceo.bind(this);
        this.onChangeboardOfDirectors = this.onChangeboardOfDirectors.bind(this);
        this.onChangebriefWriteup = this.onChangebriefWriteup.bind(this);
        this.onChangesectorName = this.onChangesectorName.bind(this);
        this.savecompany = this.savecompany.bind(this);
        this.newcompany = this.newcompany.bind(this);
        this.onChangeSectorDropdown = this.onChangeSectorDropdown.bind(this);
        this.state = {
            companyName: "",
            turnover: 0,
            ceo: "",
            boardOfDirectors: "",
            briefWriteup: "",
            sectorName: "",
            submitted: false,
            companylist: [],
            sectors:[],
            selectedSector:"",
            validationError:""
        };
    }
    componentDidMount() {
        IPOService.getAllCompany().then((response => {
            this.setState({ companylist: response.data })
        }));
        IPOService.getAllSector().then((response => {
            this.setState({ sectors: response.data })
        }));
    
    }


    onChangecompanyName(e) {
        this.setState({
            companyName: e.target.value
        });
    }

    onChangeturnover(e) {
        this.setState({
            turnover: e.target.value
        });
    }
    onChangeceo(e) {
        this.setState({
            ceo: e.target.value
        });
    }
    onChangeboardOfDirectors(e) {
        this.setState({
            boardOfDirectors: e.target.value
        });
    }
    onChangebriefWriteup(e) {
        this.setState({
            briefWriteup: e.target.value
        });
    }
    onChangesectorName(e) {
        this.setState({
            sectorName: e.target.value
        });
    }
    onChangeSectorDropdown(e){
        this.setState({
            selectedSector:e.target.value,
            sectorName:e.target.value,
            validationError:e.target.value === ""?"You must select a sector":""
        });
    }

    savecompany() {
        var data = {
            companyName: this.state.companyName,
            turnover: this.state.turnover,
            ceo: this.state.ceo,
            boardOfDirectors: this.state.boardOfDirectors,
            briefWriteup: this.state.briefWriteup,
            sectorName: this.state.sectorName
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
        // fetch('https://stockmarketcharting.herokuapp.com/company', requestOptions)
        fetch('https://stockmarketcharting-react.herokuapp.com/company', requestOptions)
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

    newcompany() {
        this.setState({
            companyName: "",
            turnover: 0,
            ceo: "",
            boardOfDirectors: "",
            briefWriteup: "",
            sectorName: "",
            submitted: false
        });
    }

    render() {
        return (
            <div>
                <div className="submit-form">
                    {this.state.submitted ? (
                        <div>
                            <h4>You submitted successfully!</h4>
                            <button className="btn btn-success" onClick={this.newcompany}>
                                Add
                            </button>
                        </div>
                    ) : (
                        <div>
                            <div className="form-group">
                                <label htmlFor="title">Company Name</label>
                                <input
                                    type="text"
                                    className="form-control"
                                    id="companyName"
                                    required
                                    value={this.state.companyName}
                                    onChange={this.onChangecompanyName}
                                    name="companyName"
                                />
                            </div>
                            <div className="form-group">
                                <label htmlFor="title">Turnover</label>
                                <input
                                    type="number"
                                    className="form-control"
                                    id="turnover"
                                    required
                                    value={this.state.turnover}
                                    onChange={this.onChangeturnover}
                                    name="turnover"
                                />
                            </div>
                            <div className="form-group">
                                <label htmlFor="title">CEO</label>
                                <input
                                    type="text"
                                    className="form-control"
                                    id="ceo"
                                    required
                                    value={this.state.ceo}
                                    onChange={this.onChangeceo}
                                    name="ceo"
                                />
                            </div>
                            <div className="form-group">
                                <label htmlFor="title">Board of Directors</label>
                                <input
                                    type="text"
                                    className="form-control"
                                    id="boardOfDirectors"
                                    required
                                    value={this.state.boardOfDirectors}
                                    onChange={this.onChangeboardOfDirectors}
                                    name="boardOfDirectors"
                                />
                            </div>
                            <div className="form-group">
                                <label htmlFor="title">About the company</label>
                                <input
                                    type="text"
                                    className="form-control"
                                    id="briefWriteup"
                                    required
                                    value={this.state.briefWriteup}
                                    onChange={this.onChangebriefWriteup}
                                    name="briefWriteup"
                                />
                            </div>
                            {/* <div className="form-group">
                                <label htmlFor="title">Sector Name</label>
                                <input
                                    type="text"
                                    className="form-control"
                                    id="sectorName"
                                    required
                                    value={this.state.sectorName}
                                    onChange={this.onChangesectorName}
                                    name="sectorName"
                                />
                            </div> */}
                            <div>
                                Select Sector
                                <select
                                
                                onClick={this.onChangeSectorDropdown}>
                                    {this.state.sectors.map((sector)=>
                                    <option key={sector.id}
                                    value={sector.sectorName}>{sector.sectorName}</option>
                                    )}
                                value={this.state.selectedSector}
                                </select>
                            </div>
                            <div style={{color:'red',marginTop:'5px'}}>
                                {this.state.validationError}
                                </div>


                            <button onClick={this.savecompany} className="btn btn-success">
                                Submit
                            </button>
                        </div>
                    )}
                </div>
                <div>
                    <h1 className="text-center"> Company Details</h1>
                    <Table className="table table-striped">
                        <thead>
                            <tr>
                                <td>Company ID</td>
                                <td>Company Name</td>
                                <td>Sector Name</td>
                                <td>Turnover</td>
                                <td>CEO</td>
                                <td>Board of Directors</td>
                                <td>Brief Writeup</td>
                                <td>Perform Actions</td>
                            </tr>
                        </thead>

                        <tbody>
                            {
                                this.state.companylist.map(
                                    companylist =>
                                        <tr key={companylist.id}>
                                            <td>{companylist.id}</td>
                                            <td>{companylist.companyName}</td>
                                            <td>{companylist.sectorName}</td>
                                            <td>{companylist.turnover}</td>
                                            <td>{companylist.ceo}</td>
                                            <td>{companylist.boardOfDirectors}</td>
                                            <td>{companylist.briefWriteup}S</td>
                                            <td><Link
                                                to={"/company/" + companylist.id}
                                                className="badge badge-warning"
                                            >
                                                Edit
                                            </Link></td>
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