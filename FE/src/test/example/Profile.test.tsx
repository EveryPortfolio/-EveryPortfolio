import React from 'react';
import { render, fireEvent } from '@testing-library/react';
import Profile from './Profile';
import '@testing-library/jest-dom/extend-expect';

describe('<Profile />', () => {
  const setup = (props: { un: string; na: string; onInsert?: any }) => {
    const utils = render(<Profile username={props.un} name={props.na} onInsert={props.onInsert} />);
    const { getByText, getByPlaceholderText } = utils;
    const input = getByPlaceholderText('입력하세요.');
    const button = getByText('제출');
    return { ...utils, input, button };
  };
  it('shows the props correctly', () => {
    const { ...utils } = setup({ un: 'Twenty', na: 'kang' });
    utils.getByText('Twenty');
    utils.getByText('(kang)');
  });

  it('has a number and two buttons', () => {
    const { ...utils } = setup({ un: 'Twenty', na: 'kang' });
    utils.getByText('0');
    utils.getByText('+1');
    utils.getByText('-1');
  });

  it('increase', () => {
    const { ...utils } = setup({ un: 'Twenty', na: 'kang' });
    const number = utils.getByText('0');
    const plusButton = utils.getByText('+1');
    fireEvent.click(plusButton);
    expect(number).toHaveTextContent('1');
    expect(number.textContent).toBe('1');
  });

  it('decrease', () => {
    const { ...utils } = setup({ un: 'Twenty', na: 'kang' });
    const number = utils.getByText('0');
    const plusButton = utils.getByText('-1');
    fireEvent.click(plusButton);
    expect(number).toHaveTextContent('-1');
  });

  it('has input and submit button', () => {
    const { input, button } = setup({ un: 'Twenty', na: 'kang' });
    expect(input).toBeTruthy();
    expect(button).toBeTruthy();
  });

  it('change input', () => {
    const { input } = setup({ un: 'Twenty', na: 'kang' });
    fireEvent.change(input, { target: { value: 'TDD도전' } });
    expect(input).toHaveAttribute('value', 'TDD도전');
  });

  it('submit text', () => {
    const onInsert = jest.fn();
    const { input, button } = setup({ un: 'Twenty', na: 'kang', onInsert });
    fireEvent.change(input, { target: { value: 'TDD도전' } });
    fireEvent.click(button);
    expect(onInsert).toBeCalledWith('TDD도전');
    expect(input).toHaveAttribute('value', '');
  });
});
