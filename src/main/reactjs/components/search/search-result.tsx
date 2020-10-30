import React from 'react';

type props = {
    searchResult: Array<searchItem>
}

export type searchItem = {
    id: string,
    name: string,
    image?: image
}

export type image = { medium: string }

export default function SearchResult(props: props) {

    const searchItems = props.searchResult.map((searchItem) => {

            return <div className={"searchResult"} key={searchItem.id}>
                { searchItem.image !== null && searchItem.image?.medium !== null &&
                    <img src={searchItem.image?.medium} height="100" width="100"/>
                }
                <p>{searchItem.name}: {searchItem.id}</p>
            </div>;
        }
    );

    return (
        <ul>{searchItems}</ul>
    );
}
