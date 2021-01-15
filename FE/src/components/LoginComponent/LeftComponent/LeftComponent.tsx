import React from 'react';
import { LeftComponentBackGround, TextDiv } from '../../common';

export const LeftComponent = (): JSX.Element => {
  console.log('render left');
  return (
    <LeftComponentBackGround>
      <TextDiv size='70px' margin='0px' text='Welcome !' />
      <TextDiv size='130px' margin='20px' text='Every Portfolio' />
      <TextDiv size='60px' margin='370px' text='Don’t have your PortFolio?' />
      <TextDiv size='30px' margin='0px' text='You can make your own portfolio easily' />
    </LeftComponentBackGround>
  );
};
