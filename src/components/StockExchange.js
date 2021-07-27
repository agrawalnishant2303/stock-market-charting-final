import React, { Component } from "react";

import IPOService from "../services/IPOService";
import Table from "react-bootstrap/Table"
export default class StockExchange extends Component {
    constructor(props) {
        super(props);
        this.onChangename = this.onChangename.bind(this);
        this.savestockexchange = this.savestockexchange.bind(this);
        this.newstockexchange = this.newstockexchange.bind(this);
        this.state = {
            name: "",
            submitted: false,
            stockexchangelist:[],
        };
    }
    componentDidMount() {
        IPOService.getAllStockExchange().then((response => {
            this.setState({ stockexchangelist: response.data })
        }));
    }
    

    onChangename(e) {
        this.setState({
            name: e.target.value
        });
    }

    savestockexchange() {
        var data = {
            name: this.state.name,
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
        //fetch('https://stockmarketcharting.herokuapp.com/stockexchange', requestOptions)
        fetch("https://stockmarketcharting-react.herokuapp.com/stockexchange",requestOptions)
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

    newstockexchange() {
        this.setState({
            name: "",
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
                        <button className="btn btn-success" onClick={this.newstockexchange}>
                            Add
                        </button>
                    </div>
                ) : (
                    <div>
                        <div className="form-group">
                            <label htmlFor="title">Stock Exchange Name</label>
                            <input
                                type="text"
                                className="form-control"
                                id="name"
                                required
                                value={this.state.name}
                                onChange={this.onChangename}
                                name="name"
                            />
                        </div>

                        <button onClick={this.savestockexchange} className="btn btn-success">
                            Submit
                        </button>
                    </div>
                )}
            </div>
            <div>
                <h1 className="text-center"> Stock Exchange Details</h1>
                <Table className="table table-striped">
                    <thead>
                        <tr>
                            <td>Stock Exchange-ID</td>
                            <td>Stock Exchange Name</td>
                        </tr>
                    </thead>

                    <tbody>
                        {
                            this.state.stockexchangelist.map(
                                stockexchangelist =>
                                    <tr key={stockexchangelist.id}>
                                        <td>{stockexchangelist.id}</td>
                                        <td>{stockexchangelist.name}</td>
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