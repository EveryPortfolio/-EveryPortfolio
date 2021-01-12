import React from 'react';
import styled from 'styled-components';

const Text = styled.div<{ size: string; margin: string }>`
  font-size: ${(props) => props.size};
  margin-top: ${(props) => props.margin};
`;

interface TextDivProps {
  size: string;
  margin: string;
  text: string;
}

export const TextDiv = ({ size, margin = '0px', text }: TextDivProps): JSX.Element => {
  return (
    <Text size={size} margin={margin}>
      {text}
    </Text>
  );
};
