import os
import re

INPUT_MD = "snippets.md"        # your big .md file containing all snippet sections
OUTPUT_DIR = "snippets_out"     # folder where .sublime-snippet files will be generated

# Create output directory if missing
os.makedirs(OUTPUT_DIR, exist_ok=True)

# Read the .md file
with open(INPUT_MD, "r", encoding="utf-8") as f:
    data = f.read()

# REGEX to capture:
# 1) filename from the header comment
# 2) snippet content inside ```xml ... ```
pattern = re.compile(
    r"#\s*(.*?)\.sublime-snippet.*?```xml(.*?)```",
    re.DOTALL
)

matches = pattern.findall(data)

print(f"Found {len(matches)} snippets.")

for filename, xml_content in matches:
    filename = filename.strip() + ".sublime-snippet"
    xml_content = xml_content.strip()

    output_path = os.path.join(OUTPUT_DIR, filename)

    with open(output_path, "w", encoding="utf-8") as f:
        f.write(xml_content)

    print(f"Generated: {output_path}")

print("\n✔ ALL DONE! Snippets saved inside:", OUTPUT_DIR)
