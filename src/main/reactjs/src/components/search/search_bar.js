// @flow

import React from "react";


type state = {}

type props = {
    visible: boolean,
    onSearch: Function
}

export default class SearchBar extends React.Component <props, state> {

    constructor(props: props) {
        super(props);
    }

    render() {
        return (

            <div className={"searchBar"} style={{visibility: this.props.visible ? 'visible' : 'hidden'}}>
                <input id="searchInput" onChange={event => this.props.onSearch(event.target.value)}/>
            </div>
        );
    }
}
