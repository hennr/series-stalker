import React from 'react';

export default function SearchResult({searchResult}) {

    const searchItems = searchResult.map((searchItem) =>
        <div key={searchItem.id}>
            <img src={searchItem.image.medium} height="100" width="100"/>
            {searchItem.name}
        </div>
    );

    return (
        <ul>{searchItems}</ul>
    );
}
