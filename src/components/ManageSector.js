import React, { Component } from "react";

import IPOService from "../services/IPOService";
import Table from "react-bootstrap/Table"
export default class ManageSector extends Component {
    constructor(props) {
        super(props);
        this.onChangesectorName = this.onChangesectorName.bind(this);
        this.savesector = this.savesector.bind(this);
        this.newsector = this.newsector.bind(this);
        this.state = {
            sectorName: "",
            brief:"",
            submitted: false,
            sectorlist:[],
        };
    }
    componentDidMount() {
        IPOService.getAllSector().then((response => {
            this.setState({ sectorlist: response.data })
        }));
    }
    

    onChangesectorName(e) {
        this.setState({
            sectorName: e.target.value
        });
    }
    onChangebrief(e) {
        this.setState({
            brief: e.target.value
        });
    }


    savesector() {
        var data = {
            sectorName: this.state.sectorName,
            brief:this.state.brief,
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
        // fetch('https://stockmarketcharting.herokuapp.com/sector', requestOptions)
        fetch('https://stockmarketcharting-react.herokuapp.com/sector', requestOptions)
            .then(response => {
                this.setState({
                    submitted:true
                });
                console.log(response.data);
            })
            .catch(e=> {
                console.log(e);
            });
    }

    newsector() {
        this.setState({
            sectorName: "",
            brief:"",
            submitted: false
        });
    }

    render() {
        return (
            <div>
            <div className="submit-form">
                {this.state.submitted ? (
                    <div>
                        <h4>Sector added successfully!</h4>
                        <button className="btn btn-success" onClick={this.newsector}>
                            Add 
                        </button>
                    </div>
                ) : (
                    <div>
                        <div className="form-group">
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
                        </div>
                        <div className="form-group">
                            <label htmlFor="title">About</label>
                            <input
                                type="text"
                                className="form-control"
                                id="brief"
                                required
                                value={this.state.brief}
                                onChange={this.onChangebrief.bind(this)}
                                name="brief"
                            />
                        </div>

                        <button onClick={this.savesector} className="btn btn-success">
                            Submit
                        </button>
                    </div>
                )}
            </div>
            <div>
                <h1 className="text-center"> Sector Details</h1>
                <Table className="table table-striped">
                    <thead>
                        <tr>
                            <td>Sector-ID</td>
                            <td>Sector Name</td>
                            <td>Brief</td>
                        </tr>
                    </thead>

                    <tbody>
                        {
                            this.state.sectorlist.map(
                                sectorlist =>
                                    <tr key={sectorlist.id}>
                                        <td>{sectorlist.id}</td>
                                        <td>{sectorlist.sectorName}</td>
                                        <td>{sectorlist.brief}</td>
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