import React from 'react';
import styled from 'styled-components';

const Wrapper = styled.div`
  width: 7%;
  height: 21%;
  position: absolute;
  left: 50%;
  top: 50%;
  transform: translate(-50%, -50%);

  button {
    width: 100%;
    height: 100%;
  }
`;

interface EditButtonProps {
  hasPortfolio: boolean;
  onClickButton: () => void;
}

export const EditButton = ({ hasPortfolio, onClickButton }: EditButtonProps): JSX.Element => {
  return (
    <Wrapper>
      <button type='button' onClick={() => onClickButton()}>
        {hasPortfolio ? '수정하기' : '생성하기'}
      </button>
    </Wrapper>
  );
};
