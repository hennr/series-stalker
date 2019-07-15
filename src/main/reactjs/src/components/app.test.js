import App from '../../src/components/app';

it('renders without crashing', () => {
  const div = document.createElement('div');
  render(<App />, div);
});
