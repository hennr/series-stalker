// @flow

import React from "react";

type props = {
    onSearch: Function
}

export default class SearchBar extends React.Component<props> {
    componentDidMount() {
        const searchInput = document.getElementById('searchInput');

        if (searchInput !== null) {
            searchInput.focus();
        }
    }

    render() {
        return (
            <div className={"searchBar"}>
                <input id="searchInput" onChange={event => this.props.onSearch(event.target.value)}/>
            </div>
        );
    }
}
