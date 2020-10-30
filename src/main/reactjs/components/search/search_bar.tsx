import React from "react";

type searchBarProps = {
    onSearch: ((searchTerm: string) => void)
}

export default class SearchBar extends React.Component<searchBarProps> {
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
