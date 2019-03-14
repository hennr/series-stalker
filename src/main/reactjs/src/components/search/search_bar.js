// @flow

import React from "react";

type props = {
    onSearch: Function
}

export default function SearchBar(props: props) {
    return (
        <div className={"searchBar"}>
            <input id="searchInput" onChange={event => props.onSearch(event.target.value)}
            />
        </div>
    );
}
