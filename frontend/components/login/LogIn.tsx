"use client";

import { Button, Dialog, Flex, Text, TextField } from "@radix-ui/themes";
import { useState } from "react";

export default function LoginDialog() {
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");

  const handleSubmit = async (e: React.FormEvent) => {
    e.preventDefault();
    // Add authentication logic here
    console.log("Login attempt with:", email, password);
  };

  return (
    <Dialog.Root>
      <Dialog.Trigger>
        <Button variant="outline" size="3">Log in</Button>
      </Dialog.Trigger>

      <Dialog.Content style={{ maxWidth: "450px" }}>
        <Dialog.Title>Log in to Turneringsportalen</Dialog.Title>
        <Dialog.Description size="2" mb="4">
          Enter your credentials to access your account.
        </Dialog.Description>

        <form onSubmit={handleSubmit}>
          <Flex direction="column" gap="3">
            <label>
              <Text as="div" size="2" mb="1" weight="bold">
                Email
              </Text>
              <TextField.Root
                placeholder="Enter your email"
                value={email}
                onChange={(e) => setEmail(e.target.value)}
              />
            </label>
            <label>
              <Text as="div" size="2" mb="1" weight="bold">
                Password
              </Text>
              <TextField.Root
                type="password"
                placeholder="Enter your password"
                value={password}
                onChange={(e) => setPassword(e.target.value)}
              />
            </label>
          </Flex>

          <Flex gap="3" mt="4" justify="end">
            <Dialog.Close>
              <Button variant="soft" color="gray">
                Cancel
              </Button>
            </Dialog.Close>
            <Button type="submit">Log in</Button>
          </Flex>
        </form>
      </Dialog.Content>
    </Dialog.Root>
  );
}
