import React from 'react';
import { render, fireEvent } from '@testing-library/react';
import LoginComponent from '../components/LoginComponent/LoginComponent';
import '@testing-library/jest-dom/extend-expect';

describe('<LoginComponent>', () => {
  const setup = () => {
    const utils = render(<LoginComponent />);
    const { getByText, getByPlaceholderText } = utils;
    const email = getByPlaceholderText('email');
    const password = getByPlaceholderText('password');
    const button = getByText('제출');
    return { ...utils, email, password, button };
  };
  it('change input', () => {
    const { email, password } = setup();
    fireEvent.change(email, { target: { value: 'email@naver.com' } });
    fireEvent.change(password, { target: { value: 'myPassword' } });
    expect(email).toHaveAttribute('value', 'email@naver.com');
    expect(password).toHaveAttribute('value', 'myPassword');
  });

  it('submit text', () => {
    const { email, password, button } = setup();
    fireEvent.change(email, { target: { value: 'email@naver.com' } });
    fireEvent.change(password, { target: { value: 'myPassword' } });
    fireEvent.click(button);
    expect(email).toHaveAttribute('value', '');
    expect(password).toHaveAttribute('value', '');
  });
});
