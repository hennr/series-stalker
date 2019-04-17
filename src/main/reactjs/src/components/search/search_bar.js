// @flow

import React from "react";

type state = {}

type props = {
    onSearch: Function
}

export default class SearchBar extends React.Component <props, state> {

    constructor(props: props) {
        super(props);
    }

    render() {
        return (
            <div className={"searchBar"}>
                <input id="searchInput" onChange={event => this.props.onSearch(event.target.value)}/>
            </div>
        );
    }
}
