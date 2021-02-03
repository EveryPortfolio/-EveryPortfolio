import React from 'react';
import { LeftComponentBackGround, TextDiv } from '../../common';

export const LeftComponent = (): JSX.Element => {
  console.log('render left');
  return (
    <LeftComponentBackGround>
      <TextDiv size='130px' margin='0px' text='Every Portfolio' />
      <TextDiv
        size='46px'
        margin='55px'
        text='
Things you can do by signing up'
      />
      <TextDiv size='30px' margin='10px' text='ㆍMake Your Portfolio' />
      <TextDiv size='30px' margin='10px' text='ㆍYou Can Get A Job' />
      <TextDiv size='30px' margin='10px' text='ㆍMake Your Team’s Project' />
      <TextDiv size='30px' margin='10px' text='ㆍShow Your Portfolio Perfectly' />
    </LeftComponentBackGround>
  );
};
