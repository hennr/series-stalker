import SearchBar from "./search_bar";
import { shallow } from 'enzyme';

describe('search bar', () => {
    it('renders without crashing', () => {
        render(<SearchBar onSearch={() => {}}/>);
    });

    it('renders input field', () => {
        const wrapper = shallow(<SearchBar/>);
        expect(wrapper.find("#searchInput")).toHaveLength(1);
    });
});
