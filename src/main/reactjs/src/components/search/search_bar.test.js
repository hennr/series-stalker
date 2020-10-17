import SearchBar from "./search_bar";
import App from "../app";

describe('search bar', () => {
    it('renders without crashing', () => {
        render(<SearchBar onSearch={() => {}}/>);
    });

    // it('renders tiles if state is found', () => {
    //     const wrapper = shallow(<SearchBar/>);
    //     expect(wrapper.find(SearchBar)).to.have.lengthOf(1);
    // });
});
