import React, { useCallback, useEffect, useState } from 'react';
import styled from 'styled-components';
import { EditButton } from './EditButton/EditButton';

const EidtButtonWrapper = styled.div`
  position: relative;
  height: 20%;
`;

const EditWrapper = styled.div`
  hegiht: 80%;
`;

const Edit = styled.div``;

export const MyPortfolio = (): JSX.Element => {
  const [hasPortfolio, setHasPortfolio] = useState(false);
  const [isClick, setIsClick] = useState(false);

  const onClickButton = useCallback(() => {
    setIsClick(!isClick);
  }, [isClick]);

  useEffect(() => {
    setHasPortfolio(false);
  }, []);

  return (
    <>
      <EidtButtonWrapper>
        <EditButton hasPortfolio={hasPortfolio} onClickButton={onClickButton} />
      </EidtButtonWrapper>
      <EditWrapper>
        <Edit />
      </EditWrapper>
    </>
  );
};
