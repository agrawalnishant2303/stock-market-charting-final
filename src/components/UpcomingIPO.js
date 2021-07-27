import React, { Component } from "react";
import IPOService from "../services/IPOService";
import Table from "react-bootstrap/Table";
export default class UpcomingIPO extends Component {
    constructor(props) {
        super(props);
        this.state = {
            ipolist: []
        };
    }
    componentDidMount() {
        IPOService.getUpcomingIPOs().then((response => {
            this.setState({ ipolist: response.data })
        }));
    }

    render() {
        return (
            <div>
                <h1 className="text-center"> Upcoming IPO Details</h1>
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
        );
    }
}