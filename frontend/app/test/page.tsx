import { Box, Container, Flex, Grid, Section } from "@radix-ui/themes";
import React from "react";

export default function TestPage() {
  return (
    <React.Fragment>
      <Grid
        style={{
          gridTemplateColumns: "repeat(2, minmax(0, 1fr))",
          width: "fit-content",
          gap: 20,
          marginBottom: 25,
          border: "1px solid red",
          padding: 8,
        }}
      >
        <Box>Test</Box>
        <Box>Test</Box>
        <Box>Test</Box>
        <Box>Test</Box>
      </Grid>
      <Flex
        style={{
          gap: 20,
          border: "1px solid blue",
          width: "fit-content",
          padding: 8,
        }}
      >
        <Box>Test</Box>
        <Box>Test</Box>
        <Box>Test</Box>
        <Box>Test</Box>
      </Flex>
      <Section
        style={{ border: "1px solid green", marginTop: 25, marginBottom: 5 }}
      >
        This is a section
      </Section>
      <Section style={{ border: "1px solid green" }}>
        This is another section
      </Section>
      <Container style={{ border: "1px solid pink", marginTop: 25 }}>
        This is a container
      </Container>
    </React.Fragment>
  );
}
